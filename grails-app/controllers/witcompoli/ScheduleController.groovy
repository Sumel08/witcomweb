package witcompoli

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

import java.text.DateFormat
import java.text.SimpleDateFormat

import grails.converters.JSON

@Transactional(readOnly = false)
class ScheduleController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Schedule.list(params), model:[scheduleCount: Schedule.count()]
    }

    def show(Schedule schedule) {
        respond schedule
    }

    def create() {
        respond new Schedule(params)
    }

    @Transactional
    def save(Schedule schedule) {
        if (schedule == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (schedule.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond schedule.errors, view:'create'
            return
        }

        schedule.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'schedule.label', default: 'Schedule'), schedule.id])
                redirect schedule
            }
            '*' { respond schedule, [status: CREATED] }
        }
    }

    def edit(Schedule schedule) {
        respond schedule
    }

    @Transactional
    def update(Schedule schedule) {
        if (schedule == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (schedule.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond schedule.errors, view:'edit'
            return
        }

        schedule.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'schedule.label', default: 'Schedule'), schedule.id])
                redirect schedule
            }
            '*'{ respond schedule, [status: OK] }
        }
    }

    @Transactional
    def delete(Schedule schedule) {

        if (schedule == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        schedule.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'schedule.label', default: 'Schedule'), schedule.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'schedule.label', default: 'Schedule'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    def schedule() {
        def event = Evento.findById(1)
        def schedule = event.schedule
        def activities = Activity.findBySchedule(schedule)

        [event: event, schedule: schedule, activities: activities]
    }

    def createActivity() {
        def activitiesType = ActivityType.findAll()
        def places = Place.findAll()
        def people = People.findAll()

        [activitiesType: activitiesType, places: places, people: people]
    }

    def saveActivity() {

        println(params)

        def event = Evento.findById(1)

        def activity = new Activity()
        def activityType = ActivityType.findById(params.activityType)

        activity.activityType = activityType

        def beginDate = params.beginDate.split("T")
        def beginTime = beginDate[1].split(":")
        beginDate = beginDate[0] + " " + beginTime[0] + ":" + beginTime[1]

        def endDate = params.endDate.split("T")
        def endTime = endDate[1].split(":")
        endDate = endDate[0] + " " + endTime[0] + ":" + endTime[1]
        
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm")
        beginDate = format.parse(beginDate)
        endDate = format.parse(endDate)

        activity.beginDate = beginDate
        activity.endDate = endDate

        activity.title = params.title
        activity.subtitle = params.subtitle
        activity.description = params.description
        activity.price = Double.parseDouble(params.price)
        activity.notes = params.notes

        def place = Place.findById(params.place)

        activity.place = place
        activity.schedule = event.schedule

        if(!activity.save()) {
            activity.errors.allErrors.each {
                println(it)
            }
        }


        if(params.speakers) {
            println(params.speakers.size())
            params.speakers.each {
                def activityPeople = new ActivityPeople()
                activityPeople.activity = activity
                activityPeople.people = People.findById(it)

                if(!activityPeople.save()) {
                    println('no se pudo guardar')
                }
                println(it)
            }
        }

        redirect(action: 'schedule')
    }

    def editSchedule() {
        def event = Evento.findById(1)
        def schedule = event.schedule
        def activities = Activity.findBySchedule(schedule)

        [evento: event, schedule: schedule, activities: activities]
    }

    def updateSchedule() {

    }

    def getSchedule() {
        def event = Evento.findById(params.id)
        def activities = Activity.findAll()
        render activities as JSON
    }
}
