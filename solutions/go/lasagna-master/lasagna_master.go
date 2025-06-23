package lasagna

func PreparationTime(layers []string, preparationTime int) int {
	if preparationTime == 0 {
		return 2 * len(layers)
	}
	return preparationTime * len(layers)
}

func Quantities(layers []string) (noodles int, sauce float64) {
	for _, layer := range layers {
		switch layer {
		case "sauce":
			sauce += 0.2
		case "noodles":
			noodles += 50
		}
	}
	return
}

func AddSecretIngredient(friendsList, myList []string) {
	myList[len(myList)-1] = friendsList[len(friendsList)-1]
}

func ScaleRecipe(quantities []float64, persons int) []float64 {
	scaledQuantities := make([]float64, len(quantities))
	coefficient := float64(persons) / 2
	for i, v := range quantities {
		scaledQuantities[i] = v * coefficient
	}
	return scaledQuantities
}
