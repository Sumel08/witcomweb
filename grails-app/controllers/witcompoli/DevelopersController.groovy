package witcompoli

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class DevelopersController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Developers.list(params), model:[developersCount: Developers.count()]
    }

    def show(Developers developers) {
        respond developers
    }

    def create() {
        respond new Developers(params)
    }

    @Transactional
    def save(Developers developers) {
        if (developers == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (developers.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond developers.errors, view:'create'
            return
        }

        developers.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'developers.label', default: 'Developers'), developers.id])
                redirect developers
            }
            '*' { respond developers, [status: CREATED] }
        }
    }

    def edit(Developers developers) {
        respond developers
    }

    @Transactional
    def update(Developers developers) {
        if (developers == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (developers.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond developers.errors, view:'edit'
            return
        }

        developers.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'developers.label', default: 'Developers'), developers.id])
                redirect developers
            }
            '*'{ respond developers, [status: OK] }
        }
    }

    @Transactional
    def delete(Developers developers) {

        if (developers == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        developers.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'developers.label', default: 'Developers'), developers.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'developers.label', default: 'Developers'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
