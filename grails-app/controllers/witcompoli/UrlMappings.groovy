package witcompoli

class UrlMappings {

    static mappings = {

        "/images/*.jpg"(controller:"image")

        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/evento/eventInfo")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
