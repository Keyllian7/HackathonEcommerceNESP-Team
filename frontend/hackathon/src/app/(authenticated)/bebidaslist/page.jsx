import { BackBar } from "../burguerlist/components/BackBar";
import { OptionsBarHome } from "./components/OptionsBarHome";
import { SearchBar } from "./components/SearchBar";
import { BebidasList } from "./components/BebidasList";

export const generateMetadata = async () => {
  return {
    title: "Hamburgueria burguer service",
    description: "Só os melhores hamburgueres encontrados aqui",
  };
};

export default function ListProducts() {
  return (
    <div className="flex flex-col min-h-screen">
      <main className="flex-grow">
        <BackBar />
        <SearchBar />
        <BebidasList />
      </main>
      <OptionsBarHome />
    </div>
  );
}
