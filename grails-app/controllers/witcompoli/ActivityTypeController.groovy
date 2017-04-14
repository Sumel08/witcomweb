package witcompoli

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = false)
class ActivityTypeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ActivityType.list(params), model:[activityTypeCount: ActivityType.count()]
    }

    def show(ActivityType activityType) {
        respond activityType
    }

    def create() {
        respond new ActivityType(params)
    }

    @Transactional
    def save(ActivityType activityType) {
        if (activityType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (activityType.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond activityType.errors, view:'create'
            return
        }

        activityType.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'activityType.label', default: 'ActivityType'), activityType.id])
                redirect activityType
            }
            '*' { respond activityType, [status: CREATED] }
        }
    }

    def edit(ActivityType activityType) {
        respond activityType
    }

    @Transactional
    def update(ActivityType activityType) {
        if (activityType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (activityType.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond activityType.errors, view:'edit'
            return
        }

        activityType.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'activityType.label', default: 'ActivityType'), activityType.id])
                redirect activityType
            }
            '*'{ respond activityType, [status: OK] }
        }
    }

    @Transactional
    def delete(ActivityType activityType) {

        if (activityType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        activityType.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'activityType.label', default: 'ActivityType'), activityType.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'activityType.label', default: 'ActivityType'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    def activities() {
        def activities = ActivityType.findAll()

        [activities: activities]
    }

    def createActivity() {

        [test: 'test']
    }

    def saveActivity() {
        def activity = new ActivityType()
        activity.name = params.name
        activity.description = params.description

        if(!activity.save()) {
            activity.errors.allErrors.each {
                println(it)
            }
        }

        redirect(action: "activities")
    }

    def editActivity() {
        def activity = ActivityType.findById(params.id)

        [activity: activity]
    }

    def updateActivity() {

        def activity = ActivityType.findById(params.idActivity)

        activity.name = params.name
        activity.description = params.description

        if(!activity.save()) {
            activity.errors.allErrors.each {
                println(it)
            }
        }

        redirect(action: "activities")
    }
}
