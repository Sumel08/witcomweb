package witcompoli

class PlaceCategory {

	String category
	String description

	static hasMany = [places: Place]

	static mapping = {
        id generator: 'identity'
        
    }

    static constraints = {
    }
}
