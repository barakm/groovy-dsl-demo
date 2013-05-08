service {
	name "MyService"
	
	lifecycle {
		install "myInstall.sh"
		start "myStart.sh"
	}
}

