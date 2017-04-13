package witcompoli

class Developers {

	static belongsTo = [evento: Evento, people: People]

	static mapping = {
        id generator: 'identity'
        
    }

    static constraints = {
    }
}
