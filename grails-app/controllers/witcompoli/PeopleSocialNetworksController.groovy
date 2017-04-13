package witcompoli

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PeopleSocialNetworksController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond PeopleSocialNetworks.list(params), model:[peopleSocialNetworksCount: PeopleSocialNetworks.count()]
    }

    def show(PeopleSocialNetworks peopleSocialNetworks) {
        respond peopleSocialNetworks
    }

    def create() {
        respond new PeopleSocialNetworks(params)
    }

    @Transactional
    def save(PeopleSocialNetworks peopleSocialNetworks) {
        if (peopleSocialNetworks == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (peopleSocialNetworks.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond peopleSocialNetworks.errors, view:'create'
            return
        }

        peopleSocialNetworks.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'peopleSocialNetworks.label', default: 'PeopleSocialNetworks'), peopleSocialNetworks.id])
                redirect peopleSocialNetworks
            }
            '*' { respond peopleSocialNetworks, [status: CREATED] }
        }
    }

    def edit(PeopleSocialNetworks peopleSocialNetworks) {
        respond peopleSocialNetworks
    }

    @Transactional
    def update(PeopleSocialNetworks peopleSocialNetworks) {
        if (peopleSocialNetworks == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (peopleSocialNetworks.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond peopleSocialNetworks.errors, view:'edit'
            return
        }

        peopleSocialNetworks.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'peopleSocialNetworks.label', default: 'PeopleSocialNetworks'), peopleSocialNetworks.id])
                redirect peopleSocialNetworks
            }
            '*'{ respond peopleSocialNetworks, [status: OK] }
        }
    }

    @Transactional
    def delete(PeopleSocialNetworks peopleSocialNetworks) {

        if (peopleSocialNetworks == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        peopleSocialNetworks.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'peopleSocialNetworks.label', default: 'PeopleSocialNetworks'), peopleSocialNetworks.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'peopleSocialNetworks.label', default: 'PeopleSocialNetworks'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
