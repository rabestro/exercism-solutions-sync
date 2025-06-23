// Package twofer contains function ShareWith(name string)
package twofer

// ShareWith returns "One for 'name', one for me."
func ShareWith(name string) string {
	if name == "" {
		name = "you"
	}
	return "One for " + name + ", one for me."
}
