package witcompoli

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

import java.text.DateFormat
import java.text.SimpleDateFormat

import grails.converters.JSON

@Transactional(readOnly = false)
class EventoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Evento.list(params), model:[eventoCount: Evento.count()]
    }

    def show(Evento evento) {
        respond evento
    }

    def create() {
        respond new Evento(params)
    }

    @Transactional
    def save(Evento evento) {
        if (evento == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (evento.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond evento.errors, view:'create'
            return
        }

        evento.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'evento.label', default: 'Evento'), evento.id])
                redirect evento
            }
            '*' { respond evento, [status: CREATED] }
        }
    }

    def edit(Evento evento) {
        respond evento
    }

    @Transactional
    def update(Evento evento) {
        if (evento == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (evento.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond evento.errors, view:'edit'
            return
        }

        evento.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'evento.label', default: 'Evento'), evento.id])
                redirect evento
            }
            '*'{ respond evento, [status: OK] }
        }
    }

    @Transactional
    def delete(Evento evento) {

        if (evento == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        evento.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'evento.label', default: 'Evento'), evento.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'evento.label', default: 'Evento'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    def eventInfo() {

        def evento = Evento.findById(1)
        println('Acá evento')
        println(evento.code)

        def chairs = Chairs.findAllByEvento(evento)

        [test: 'test', evento: evento, chairs: chairs]
    }

    def editEvent() {

        def evento = Evento.findById(1)
        println('Acá evento')
        println(evento.code)

        def chairs = Chairs.findAllByEvento(evento)

        def places = Place.findAll()
        def people = People.findAll()

        [places: places, evento: evento, chairs: chairs, people: people]
    }

    def updateEvent() {
        println(params)

        def evento = Evento.findById(1)
        //def list = evento.chairs.id

        
        //def list2 = [12]
        
        
        //println(list2)
        Chairs.findAllByEvento(evento)*.delete()

        if (params.flag == "0") {

            evento.code = params.eventCode
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

            def place = Place.findById(params.place)
            evento.place = place

            println('before')
            params.chairs.each {
                def chair = new Chairs()
                chair.evento = evento
                chair.people = People.findById(it)

                if(!chair.save()){
                    println('Algo pasó')
                }
            }
            println('after')

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
            }
        } else {
            
            if(!evento.place)
                evento.place = new Place()

            ////PLACE FOR CHAIR////
            def place = evento.place
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

            if(!evento.save()) {
                evento.errors.allErrors.each {
                    println(it)
                }
            }

            ///////////////////////
        }

        redirect(action: "editEvent")
    }

    def getEvent() {
        def event = Evento.findById(1)

        render event as JSON
    }
}
