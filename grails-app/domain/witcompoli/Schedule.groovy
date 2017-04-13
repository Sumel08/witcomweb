package witcompoli

class Schedule {

	Date dateCreated

	static hasMany = [activities: Activity]

	static belongsTo = [evento: Evento]

	static mapping = {
        id generator: 'identity'
        
    }

    static constraints = {
    }
}
