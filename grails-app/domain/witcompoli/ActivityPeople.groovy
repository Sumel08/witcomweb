package witcompoli

class ActivityPeople {

	static belongsTo = [activity: Activity, people: People]

    static constraints = {
    	id generator: 'identity'
    }
}
