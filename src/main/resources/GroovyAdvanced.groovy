service {
	name "MyService"
	
	println "Hello Groovy DSL World"
	
	lifecycle {
		install myinstall
		start mystart
	}
}

