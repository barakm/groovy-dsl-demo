service {
	name "MyService" + "hello"
	
	lifecycle {
		install "myInstall.sh"
		start "myStart.sh"
	}
}

