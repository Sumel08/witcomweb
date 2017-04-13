package witcompoli

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TourismController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Tourism.list(params), model:[tourismCount: Tourism.count()]
    }

    def show(Tourism tourism) {
        respond tourism
    }

    def create() {
        respond new Tourism(params)
    }

    @Transactional
    def save(Tourism tourism) {
        if (tourism == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (tourism.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond tourism.errors, view:'create'
            return
        }

        tourism.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'tourism.label', default: 'Tourism'), tourism.id])
                redirect tourism
            }
            '*' { respond tourism, [status: CREATED] }
        }
    }

    def edit(Tourism tourism) {
        respond tourism
    }

    @Transactional
    def update(Tourism tourism) {
        if (tourism == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (tourism.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond tourism.errors, view:'edit'
            return
        }

        tourism.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'tourism.label', default: 'Tourism'), tourism.id])
                redirect tourism
            }
            '*'{ respond tourism, [status: OK] }
        }
    }

    @Transactional
    def delete(Tourism tourism) {

        if (tourism == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        tourism.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'tourism.label', default: 'Tourism'), tourism.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'tourism.label', default: 'Tourism'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
