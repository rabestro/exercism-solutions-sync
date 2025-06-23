package lasagna

const (
	defaultPreparationTimePerLayer = 2
	noodleQtyPerLayer              = 50
	sauceQtyPerLayer               = 0.2
	defaultServingInRecipe         = 2
)

// PreparationTime estimates the total preparation time based on the number of layers
func PreparationTime(layers []string, preparationTime int) int {
	if preparationTime == 0 {
		preparationTime = defaultPreparationTimePerLayer
	}
	return preparationTime * len(layers)
}

// Quantities determines the quantity of noodles and sauce needed to make your meal
func Quantities(layers []string) (noodles int, sauce float64) {
	for _, layer := range layers {
		switch layer {
		case "sauce":
			sauce += sauceQtyPerLayer
		case "noodles":
			noodles += noodleQtyPerLayer
		}
	}
	return
}

func AddSecretIngredient(friendsList, myList []string) {
	myList[len(myList)-1] = friendsList[len(friendsList)-1]
}

func ScaleRecipe(quantities []float64, persons int) []float64 {
	scaledQuantities := make([]float64, len(quantities))
	coefficient := float64(persons) / defaultServingInRecipe
	for i, quantity := range quantities {
		scaledQuantities[i] = quantity * coefficient
	}
	return scaledQuantities
}
