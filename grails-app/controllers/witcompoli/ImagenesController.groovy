package witcompoli

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

import grails.converters.JSON

@Transactional(readOnly = true)
class ImagenesController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Imagenes.list(params), model:[imagenesCount: Imagenes.count()]
    }

    def show(Imagenes imagenes) {
        respond imagenes
    }

    def create() {
        respond new Imagenes(params)
    }

    @Transactional
    def save(Imagenes imagenes) {
        if (imagenes == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (imagenes.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond imagenes.errors, view:'create'
            return
        }

        imagenes.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'imagenes.label', default: 'Imagenes'), imagenes.id])
                redirect imagenes
            }
            '*' { respond imagenes, [status: CREATED] }
        }
    }

    def edit(Imagenes imagenes) {
        respond imagenes
    }

    @Transactional
    def update(Imagenes imagenes) {
        if (imagenes == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (imagenes.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond imagenes.errors, view:'edit'
            return
        }

        imagenes.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'imagenes.label', default: 'Imagenes'), imagenes.id])
                redirect imagenes
            }
            '*'{ respond imagenes, [status: OK] }
        }
    }

    @Transactional
    def delete(Imagenes imagenes) {

        if (imagenes == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        imagenes.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'imagenes.label', default: 'Imagenes'), imagenes.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'imagenes.label', default: 'Imagenes'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    def images() {

        println(params)

        def filename = "images/" + params.id + "." + params.format
        println(filename)

        File eventImage = new File(filename)

        InputStream targetStream = new FileInputStream(eventImage);

        render file: targetStream, contentType: 'image/' + params.format

        //render "some text"
    }

    def getImages() {
        def images = Imagenes.findAll()

        render images as JSON
    }
}
