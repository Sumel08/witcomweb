package witcompoli

class Streams {

	String url
	String description
	Evento event

	static belongsTo = [Evento]

	static mapping = {
        id generator: 'identity'
        
    }

    static constraints = {
    	description nullable: true
    }
}
