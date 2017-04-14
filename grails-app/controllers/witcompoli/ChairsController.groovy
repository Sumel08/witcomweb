package witcompoli

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = false)
class ChairsController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Chairs.list(params), model:[chairsCount: Chairs.count()]
    }

    def show(Chairs chairs) {
        respond chairs
    }

    def create() {
        respond new Chairs(params)
    }

    @Transactional
    def save(Chairs chairs) {
        if (chairs == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (chairs.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond chairs.errors, view:'create'
            return
        }

        chairs.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'chairs.label', default: 'Chairs'), chairs.id])
                redirect chairs
            }
            '*' { respond chairs, [status: CREATED] }
        }
    }

    def edit(Chairs chairs) {
        respond chairs
    }

    @Transactional
    def update(Chairs chairs) {
        if (chairs == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (chairs.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond chairs.errors, view:'edit'
            return
        }

        chairs.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'chairs.label', default: 'Chairs'), chairs.id])
                redirect chairs
            }
            '*'{ respond chairs, [status: OK] }
        }
    }

    @Transactional
    def delete(Chairs chairs) {

        if (chairs == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        chairs.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'chairs.label', default: 'Chairs'), chairs.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'chairs.label', default: 'Chairs'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    def editChair() {

        render 'ok'
    }
}
