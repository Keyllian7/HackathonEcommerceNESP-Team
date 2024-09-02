"use client"

import { useRouter } from "next/navigation";

export const ProductTypeSelector = () => {

    const router = useRouter();

    return (
        <div>
            <h1>ProductTypeSelector</h1>

            <h2 onClick={() => router.push("/?filter=burgers")}>Burgers</h2>
            <h2 onClick={() => router.push("/?filter=drinks")}>Drinks</h2>
        </div>
    );
}