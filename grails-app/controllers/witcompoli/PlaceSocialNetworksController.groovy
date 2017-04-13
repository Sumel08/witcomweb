package witcompoli

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PlaceSocialNetworksController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond PlaceSocialNetworks.list(params), model:[placeSocialNetworksCount: PlaceSocialNetworks.count()]
    }

    def show(PlaceSocialNetworks placeSocialNetworks) {
        respond placeSocialNetworks
    }

    def create() {
        respond new PlaceSocialNetworks(params)
    }

    @Transactional
    def save(PlaceSocialNetworks placeSocialNetworks) {
        if (placeSocialNetworks == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (placeSocialNetworks.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond placeSocialNetworks.errors, view:'create'
            return
        }

        placeSocialNetworks.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'placeSocialNetworks.label', default: 'PlaceSocialNetworks'), placeSocialNetworks.id])
                redirect placeSocialNetworks
            }
            '*' { respond placeSocialNetworks, [status: CREATED] }
        }
    }

    def edit(PlaceSocialNetworks placeSocialNetworks) {
        respond placeSocialNetworks
    }

    @Transactional
    def update(PlaceSocialNetworks placeSocialNetworks) {
        if (placeSocialNetworks == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (placeSocialNetworks.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond placeSocialNetworks.errors, view:'edit'
            return
        }

        placeSocialNetworks.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'placeSocialNetworks.label', default: 'PlaceSocialNetworks'), placeSocialNetworks.id])
                redirect placeSocialNetworks
            }
            '*'{ respond placeSocialNetworks, [status: OK] }
        }
    }

    @Transactional
    def delete(PlaceSocialNetworks placeSocialNetworks) {

        if (placeSocialNetworks == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        placeSocialNetworks.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'placeSocialNetworks.label', default: 'PlaceSocialNetworks'), placeSocialNetworks.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'placeSocialNetworks.label', default: 'PlaceSocialNetworks'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
