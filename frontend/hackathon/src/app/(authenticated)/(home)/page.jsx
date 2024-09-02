import { NavBar } from "./components/NavBar";
import { ChooseMeal } from "./components/ChooseMeal";
import { BestSellers } from "./components/BestSellers";

export const generateMetadata = async () => {
  return {
    title: "Hamburgueria burguer service",
    description: "Só os melhores hamburgueres encontrados aqui",
  };
}

export default function LandingPage() {
  return (
    <main>
      <NavBar />
      <ChooseMeal />
      <BestSellers />
    </main>
  );
}
