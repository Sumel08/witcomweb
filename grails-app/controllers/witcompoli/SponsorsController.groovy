package witcompoli

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class SponsorsController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Sponsors.list(params), model:[sponsorsCount: Sponsors.count()]
    }

    def show(Sponsors sponsors) {
        respond sponsors
    }

    def create() {
        respond new Sponsors(params)
    }

    @Transactional
    def save(Sponsors sponsors) {
        if (sponsors == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (sponsors.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond sponsors.errors, view:'create'
            return
        }

        sponsors.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'sponsors.label', default: 'Sponsors'), sponsors.id])
                redirect sponsors
            }
            '*' { respond sponsors, [status: CREATED] }
        }
    }

    def edit(Sponsors sponsors) {
        respond sponsors
    }

    @Transactional
    def update(Sponsors sponsors) {
        if (sponsors == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (sponsors.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond sponsors.errors, view:'edit'
            return
        }

        sponsors.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'sponsors.label', default: 'Sponsors'), sponsors.id])
                redirect sponsors
            }
            '*'{ respond sponsors, [status: OK] }
        }
    }

    @Transactional
    def delete(Sponsors sponsors) {

        if (sponsors == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        sponsors.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'sponsors.label', default: 'Sponsors'), sponsors.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'sponsors.label', default: 'Sponsors'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
