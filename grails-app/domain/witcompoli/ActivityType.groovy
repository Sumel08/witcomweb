package witcompoli

class ActivityType {

	String name
	String description
	Date dateCreated

	static hasMany = [activities: Activity]

	static mapping = {
        id generator: 'identity'
        version false
        
    }

    static constraints = {
    }
}
