import { Modal } from "@mui/material"

export const ObservationModal = ({ handleOpenModal, handleCloseModal, setObservation }) => {

    const handleSubmit = (e) => {
        e.preventDefault()
        
        const newObservation = e.target.observation.value
        
        setObservation(newObservation)

        handleCloseModal()
    }

    return (
        <Modal open={handleOpenModal} onClose={handleCloseModal}>
            <div className="flex justify-center items-center h-full">
                <form onSubmit={handleSubmit} className="flex flex-col bg-white p-4 rounded-lg gap-2">
                    <label>Observação:</label>
                    <textarea
                        className="rounded-lg resize-none outline-none p-2 placeholder:text-gray-600 placeholder:text-sm border border-gray-400/75"
                        name="observation"
                        id="observation"
                        cols="30"
                        rows="10"
                        placeholder="Não colocar cebola, alface e tomate."
                    />
                    <button className="bg-slate-600 hover:bg-slate-600/70 text-white transition-colors py-2 px-4 rounded-lg" type="submit">
                        Adicionar
                    </button>
                </form>
            </div>
        </Modal>
    )
}