import Image from "next/image";
import Link from "next/link";

export const SearchBar = () => {
  return (
    <div className="flex flex-col items-center bg-quaternary p-6">
      <header className="flex justify-center mb-6">
        <h1 className="text-white text-2xl font-bold">Cardápio Burguers</h1>
      </header>

      <div className="flex items-center mb-6 mt-6">
        <input
          type="text"
          placeholder="Procure seu Burguer"
          className="p-2 rounded-l-lg outline-none w-64"
        />
        <button className="bg-white p-2 rounded-r-lg">
          <svg width="24" height="24" fill="currentColor">
            <path d="M10 2a8 8 0 0 1 8 8 8 8 0 0 1-1.641 4.829l4.829 4.828a1 1 0 0 1-1.415 1.415l-4.828-4.829A8 8 0 1 1 10 2zm0 2a6 6 0 1 0 0 12 6 6 0 0 0 0-12z" />
          </svg>
        </button>
      </div>
    </div>
  );
};
