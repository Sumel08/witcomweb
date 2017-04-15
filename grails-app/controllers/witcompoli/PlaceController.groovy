package witcompoli

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

import grails.converters.JSON

@Transactional(readOnly = false)
class PlaceController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Place.list(params), model:[placeCount: Place.count()]
    }

    def show(Place place) {
        respond place
    }

    def create() {
        respond new Place(params)
    }

    @Transactional
    def save(Place place) {
        if (place == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (place.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond place.errors, view:'create'
            return
        }

        place.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'place.label', default: 'Place'), place.id])
                redirect place
            }
            '*' { respond place, [status: CREATED] }
        }
    }

    def edit(Place place) {
        respond place
    }

    @Transactional
    def update(Place place) {
        if (place == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (place.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond place.errors, view:'edit'
            return
        }

        place.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'place.label', default: 'Place'), place.id])
                redirect place
            }
            '*'{ respond place, [status: OK] }
        }
    }

    @Transactional
    def delete(Place place) {

        if (place == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        place.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'place.label', default: 'Place'), place.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'place.label', default: 'Place'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    def places() {
        def places = Place.findAll()

        println('en places')

        [places: places]
    }

    def createPlace() {

    }

    def savePlace() {
        println(params)


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

        redirect(action: "places")
    }

    def editPlace() {
        def place = Place.findById(params.id)

        println(params.id)

        [place: place]
    }

    def updatePlace() {
        println(params)


        ////PLACE FOR CHAIR////
        def place = Place.findById(params.idPlace)
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

        redirect(action: "places")
    }

    def getPlaces() {
        def places = Place.findAll()

        render places as JSON
    }
}
