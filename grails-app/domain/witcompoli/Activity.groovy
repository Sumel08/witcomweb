package witcompoli

class Activity {

	String title
	String subtitle
	String description
	String notes
	Double price
	Date beginDate
	Date endDate
	
	static hasMany = [activityPeople: ActivityPeople]

	static belongsTo = [activityType: ActivityType, place: Place, schedule: Schedule]

	static mapping = {
        id generator: 'identity'
        
    }

    static constraints = {
    }
}
