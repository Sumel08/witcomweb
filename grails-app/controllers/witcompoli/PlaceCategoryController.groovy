package witcompoli

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

import grails.converters.JSON

@Transactional(readOnly = false)
class PlaceCategoryController {

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

    def placeCategories() {
        def placeCategories = PlaceCategory.findAll()


        [placeCategories: placeCategories]
    }

    def createPlaceCategory() {

    }

    def savePlaceCategory() {
        println(params)

        def placeCategory = new PlaceCategory()
        placeCategory.category = params.category
        placeCategory.description = params.description

        if(!placeCategory.save()) {
            placeCategory.errors.allErrors.each {
                println(it)
            }
        }
        

        redirect(action: "placeCategories")
    }

    def editPlaceCategory() {
        def placeCategory = PlaceCategory.findById(params.id)


        [placeCategory: placeCategory]
    }

    def updatePlaceCategory() {
        println(params)


        ////PLACE FOR CHAIR////
        def placeCategory = PlaceCategory.findById(params.idPlaceCategory)
        placeCategory.category = params.category
        placeCategory.description = params.description

        if(!placeCategory.save()) {
            placeCategory.errors.allErrors.each {
                println(it)
            }
        }

        redirect(action: "placeCategories")
    }

    def getPlaceCategories() {
        def placeCategories = PlaceCategory.findAll()

        render placeCategories as JSON
    }
}
