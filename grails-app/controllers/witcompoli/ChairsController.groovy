package witcompoli

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

import java.text.DateFormat
import java.text.SimpleDateFormat

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

    def createChair() {

        def event = Evento.findById(params.id)

        [event: event]
    }

    def saveChair() {

        println(params)

        ////EVENTO/////
        def event = Evento.findById(params.idEvent)
        ///////////////

        ////PLACE FOR CHAIR////
        def place = new Place()
        place.placeName = params.placeName
        place.description = params.placeDescription
        place.telephone = params.placePhone
        place.email = params.placeEmail
        place.website = params.placeWebsite
        place.indication = params.placeIndications
        place.additionalInfo = params.placeAdditional
        place.latitude = params.latitude
        place.longitude = params.longitude
        place.altitude = params.altitude

        ////PHOTO FOR PLACE////
        try {

            def placeImage = request.getFile("placeImage")


            println(params.placeImage.filename)

            File placePhoto = new File("images/" + params.placeImage.filename)

            FileOutputStream fos = new FileOutputStream(placePhoto);
            fos.write(placeImage.getBytes());

            println(placePhoto.absolutePath)

            def photo = new Imagenes()

            photo.url = "/imagenes/images/" + params.placeImage.filename

            if (!photo.save()) {
                photo.errors.allErrors.each {
                    println(it)
                }
            }

            place.image = photo
        } catch (FileNotFoundException e) {
            println('algo pasó')
        }
        ///////////////////////
        if(!place.save()) {
            place.errors.allErrors.each {
                println(it)
            }
        }

        ///////////////////////

        ////PERSON////
        def person = new People()

        person.name = params.name
        person.surname = params.surname
        person.email = params.email
        person.phone = params.phone
        person.resume = params.resume

        def birthdate = params.dateOfBirth
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd")
        birthdate = format.parse(birthdate)

        person.birthdate = birthdate

        ////PHOTO FOR PERSON////
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

            def personPhoto = new Imagenes()

            personPhoto.url = "/imagenes/images/" + params.profilePhoto.filename

            if(!personPhoto.save()) {
                personPhoto.errors.allErrors.each {
                    println(it)
                }
            }

            person.photo = personPhoto
        } catch (FileNotFoundException e) {
            println('algo pasó')
        }
        ////////////////////////

        person.provenance = place

        if(!person.save()) {
            person.errors.allErrors.each {
                println(it)
            }
        }
        //////////////
        def chair = new Chairs()

        chair.evento = event
        chair.people = person

        if(!chair.save()) {
            chair.errors.allErrors.each {
                println(chair)
            }
        }
        ////CHAIR////

        /////////////

        redirect(controller: "evento", action: "eventInfo")
    }

    def deleteChair() {

        def chair =  Chairs.findById(params.id)
        /*def people = chair.people
        def provenance = chair.people.provenance
        def placePhoto = chair.people.provenance.image
        def profilePhoto = chair.people.photo*/
        
        chair.delete()
        /*people.delete()
        provenance.delete()
        placePhoto.delete()
        profilePhoto.delete()*/

        println(params.id)

        redirect(controller: "evento", action: "eventInfo")
    }
}
