package witcompoli

class People {

	String name
	String surname
	Date birthdate
	Imagenes photo
	String resume
	String email
	String phone
	Place provenance

	static hasMany = [peopleSocialNetworks: PeopleSocialNetworks, activityPeople: ActivityPeople]

	static mapping = {
        id generator: 'identity'
        
    }

    static constraints = {
    	surname nullable: true
    	birthdate nullable: true
    	photo nullable: true
    	phone nullable: true
    	provenance nullable: true
    }
}
