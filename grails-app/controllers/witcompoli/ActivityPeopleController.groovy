package witcompoli

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ActivityPeopleController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ActivityPeople.list(params), model:[activityPeopleCount: ActivityPeople.count()]
    }

    def show(ActivityPeople activityPeople) {
        respond activityPeople
    }

    def create() {
        respond new ActivityPeople(params)
    }

    @Transactional
    def save(ActivityPeople activityPeople) {
        if (activityPeople == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (activityPeople.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond activityPeople.errors, view:'create'
            return
        }

        activityPeople.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'activityPeople.label', default: 'ActivityPeople'), activityPeople.id])
                redirect activityPeople
            }
            '*' { respond activityPeople, [status: CREATED] }
        }
    }

    def edit(ActivityPeople activityPeople) {
        respond activityPeople
    }

    @Transactional
    def update(ActivityPeople activityPeople) {
        if (activityPeople == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (activityPeople.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond activityPeople.errors, view:'edit'
            return
        }

        activityPeople.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'activityPeople.label', default: 'ActivityPeople'), activityPeople.id])
                redirect activityPeople
            }
            '*'{ respond activityPeople, [status: OK] }
        }
    }

    @Transactional
    def delete(ActivityPeople activityPeople) {

        if (activityPeople == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        activityPeople.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'activityPeople.label', default: 'ActivityPeople'), activityPeople.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'activityPeople.label', default: 'ActivityPeople'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
