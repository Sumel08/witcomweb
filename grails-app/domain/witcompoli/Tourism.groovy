package witcompoli

class Tourism {

	static belongsTo = [evento: Evento, place: Place]

	static mapping = {
        id generator: 'identity'
        
    }

    static constraints = {
    }
}
