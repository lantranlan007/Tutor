package visitor.example2;

public interface VehicleInspector {
	// visits car
	int visit(Car car);

	// visits van
	int visit(Van van);

	// visits motor bike
	int visit(Motorbike motorbike);
}
