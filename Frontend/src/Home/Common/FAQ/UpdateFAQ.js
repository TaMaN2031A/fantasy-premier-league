import { React, useState } from "react";
import  Modal  from "./Modal";

function UpdateFAQ(props) {
  const [isModalOpen, setIsModalOpen] = useState(false);

  const openModal = () => {
    setIsModalOpen(true);
  };

  const closeModal = () => {
    setIsModalOpen(false);
  };
  
  return (
    <>
      <button
        type="button"
        onClick={openModal}
        className="focus:outline-none w-48 h-16 text-white  bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-green-300 font-medium rounded-lg text-xl px-10 py-2.5 me-2 mb-2"
      >
        Update
      </button>
      <Modal isOpen={isModalOpen} closeModal={closeModal} faqID={props.faqID} func={props.func} />
    </>
  );
}

export default UpdateFAQ;
