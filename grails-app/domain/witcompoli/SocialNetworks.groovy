package witcompoli

class SocialNetworks {

	String url
	String type

	static hasMany = [peopleSocialNetworks: PeopleSocialNetworks, placeSocialNetworks: PlaceSocialNetworks]

	static mapping = {
        id generator: 'identity'
        
    }

    static constraints = {
    }
}
