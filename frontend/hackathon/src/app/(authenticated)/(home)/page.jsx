import { NavBar } from "./components/NavBar";
import { ChooseMeal } from "./components/ChooseMeal";
import { BestSellers } from "./components/BestSellers";
import { Promotion } from "./components/Promotion";
import { OptionsBar } from "./components/OptionsBar";

export const generateMetadata = async () => {
  return {
    title: "Hamburgueria burguer service",
    description: "Só os melhores hamburgueres encontrados aqui",
  };
}

export default function LandingPage() {
  return (
    <div className="flex flex-col min-h-screen">
      <main className="flex-grow"> 
        <NavBar />
        <ChooseMeal />
        <BestSellers />
        <Promotion />
      </main>
        <OptionsBar/>
    </div>
  );
}
