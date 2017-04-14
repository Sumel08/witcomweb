package witcompoli

class Evento {

	String name
	String code
	String description
	Date dateCreated
	Date startDate
	Date endDate
	Imagenes eventImage
	Imagenes sketch
	Place place
	Schedule schedule

	static hasMany = [streams: Streams, developers: Developers, tourism: Tourism, chairs: Chairs]

	static mapping = {
        id generator: 'identity'
        
    }

    static constraints = {
    	code unique: true
    	eventImage nullable: true
    	sketch nullable: true
    	place nullable: true
    	schedule nullable: true
    	description(maxSize:1000)
    }
}
