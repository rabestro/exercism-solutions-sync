package airportrobot

type Greeter interface {
	LanguageName() string
	Greet(name string) string
}

func SayHello(name string, greeter Greeter) string {
	return "I can speak " + greeter.LanguageName() + ": " + greeter.Greet(name)
}

type Italian struct{}

func (s Italian) LanguageName() string {
	return "Italian"
}

func (s Italian) Greet(name string) string {
	return "Ciao " + name + "!"
}

type Portuguese struct{}

func (s Portuguese) LanguageName() string {
	return "Portuguese"
}

func (s Portuguese) Greet(name string) string {
	return "Olá " + name + "!"
}
