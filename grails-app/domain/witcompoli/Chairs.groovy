package witcompoli

class Chairs {

	static belongsTo = [evento: Evento, people: People]

	static mapping = {
        id generator: 'identity'
        
    }

    static constraints = {
    }
}
