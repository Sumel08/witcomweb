package witcompoli

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PeopleController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond People.list(params), model:[peopleCount: People.count()]
    }

    def show(People people) {
        respond people
    }

    def create() {
        respond new People(params)
    }

    @Transactional
    def save(People people) {
        if (people == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (people.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond people.errors, view:'create'
            return
        }

        people.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'people.label', default: 'People'), people.id])
                redirect people
            }
            '*' { respond people, [status: CREATED] }
        }
    }

    def edit(People people) {
        respond people
    }

    @Transactional
    def update(People people) {
        if (people == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (people.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond people.errors, view:'edit'
            return
        }

        people.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'people.label', default: 'People'), people.id])
                redirect people
            }
            '*'{ respond people, [status: OK] }
        }
    }

    @Transactional
    def delete(People people) {

        if (people == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        people.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'people.label', default: 'People'), people.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'people.label', default: 'People'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
