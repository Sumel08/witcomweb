package witcompoli

class PeopleSocialNetworks {

	static belongsTo = [people: People, socialNetworks: SocialNetworks]

    static constraints = {
    	id generator: 'identity'
    }
}
