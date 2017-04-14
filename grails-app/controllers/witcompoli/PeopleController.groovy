package witcompoli

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

import java.text.DateFormat
import java.text.SimpleDateFormat

@Transactional(readOnly = false)
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

    def editPerson() {
        println(params.id)
        //render 'Editing person'

        def person = People.findById(params.id)

        [person: person]
    }

    def updatePerson() {
        println(params)

        def person = People.findById(params.idPerson)

        println(person)

        person.name = params.name
        person.surname = params.surname
        person.email = params.email
        person.phone = params.phone
        person.resume = params.resume

        def birthdate = params.dateOfBirth
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd")
        birthdate = format.parse(birthdate)

        person.birthdate = birthdate

        try {

            def something = request.getFile("profilePhoto")


            println(params.profilePhoto.filename)

            File profilePhoto = new File("images/" + params.profilePhoto.filename)

            //something.transferTo(eventImage)
            FileOutputStream fos = new FileOutputStream(profilePhoto);
            fos.write(something.getBytes());

            println(profilePhoto.absolutePath)

            //InputStream targetStream = new FileInputStream(eventImage);

            //render file: targetStream, contentType: 'image/png'

            person.photo.url = "/imagenes/images/" + params.profilePhoto.filename
        } catch (FileNotFoundException e) {
            println('algo pasó')
        } finally {
            println('Guardando')
        if (!person.save()) {
            person.errors.allErrors.each {
                    println(it)
                }
            }
        }
        
        //def startTime = startDate[1].split(":")
        //startDate = startDate[0] + " " + startTime[0] + ":" + startTime[1]


        /*evento.code = params.eventCode
        evento.description = params.description
        evento.name = params.eventName

        println(params.startDate)
        def startDate = params.startDate.split("T")
        def startTime = startDate[1].split(":")
        startDate = startDate[0] + " " + startTime[0] + ":" + startTime[1]

        def endDate = params.endDate.split("T")
        def endTime = endDate[1].split(":")
        endDate = endDate[0] + " " + endTime[0] + ":" + endTime[1]
        
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm")
        startDate = format.parse(startDate)
        endDate = format.parse(endDate)

        evento.startDate = startDate
        evento.endDate = endDate

        try {

            def something = request.getFile("eventPhoto")


            println(params.eventPhoto.filename)

            File eventImage = new File("images/" + params.eventPhoto.filename)

            //something.transferTo(eventImage)
            FileOutputStream fos = new FileOutputStream(eventImage);
            fos.write(something.getBytes());

            println(eventImage.absolutePath)

            //InputStream targetStream = new FileInputStream(eventImage);

            //render file: targetStream, contentType: 'image/png'

            evento.eventImage.url = "/imagenes/images/" + params.eventPhoto.filename
        } catch (FileNotFoundException e) {

        } finally {
            println('Guardando')
        if (!evento.save()) {
            evento.errors.allErrors.each {
                    println(it)
                }
            }
        */
        render 'ok'
    }
}
