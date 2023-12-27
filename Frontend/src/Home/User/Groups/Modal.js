import { React, useState } from "react";
import { createGroup } from "../../../Services/Groups/Groups";
import { GetAuthDataFn } from "../../../Routes/wrapper";
import { toast, ToastContainer } from "react-toastify";
import { toastStyle } from "../../../collection";

const Modal = ({ isOpen, closeModal, func }) => {

  const { person } = GetAuthDataFn();
  const [groupName, setGroupName] = useState("");
  const [visibility, setVisibility] = useState("public"); // 'public' as the default value

  const handleGroupNameChange = (e) => {
    setGroupName(e.target.value);
  };

  const handleVisibilityChange = (e) => {
    setVisibility(e.target.value);
  };

  const handleSave = async () => {
    const newId = await createGroup({ groupName, isPrivate: visibility === "public" ? 0 : 1, userName: person.username });
    // check if the newId is not null or undefined
    console.log("new id = ", newId.Id);
    if (newId) {
      toast.success("New Group Id = " + newId, toastStyle);
    }
    setGroupName("");
    setVisibility("public");
    closeModal();
    await func();
  };

  const cancelSave = () => {
    setGroupName("");
    setVisibility("public");
    closeModal();
  };

  return (
    <div
      className={`fixed z-10 inset-0 overflow-y-auto ${
        isOpen ? "block" : "hidden"
      }`}
    >
      <div className="flex items-center justify-center min-h-screen pt-4 px-4 pb-20 text-center">
        <div className="fixed inset-0 transition-opacity" aria-hidden="true">
          <div className="absolute inset-0 bg-gray-500 opacity-75" />
        </div>
        <span
          className="hidden sm:inline-block sm:align-middle sm:h-screen"
          aria-hidden="true"
        >
          &#8203;
        </span>
        <div
          className="inline-block align-bottom bg-white rounded-lg text-left overflow-hidden shadow-xl transform transition-all sm:my-8 sm:align-middle sm:max-w-lg sm:w-full"
          role="dialog"
          aria-modal="true"
          aria-labelledby="modal-headline"
        >
          <div className="bg-white px-4 pt-5 pb-4 sm:p-6 sm:pb-4">
            <div className="sm:flex sm:items-start">
              <div className="mt-3 text-center sm:mt-0 sm:ml-4 sm:text-left">
                <h3
                  className="text-lg leading-6 font-medium text-gray-900"
                  id="modal-headline"
                >
                  Create New Group
                </h3>
                <div className="mt-2">
                  <input
                    type="text"
                    value={groupName}
                    onChange={handleGroupNameChange}
                    className="w-full border rounded-md px-3 py-2 mt-2 focus:outline-none focus:ring focus:border-blue-300"
                    placeholder="Group name"
                  />
                  <select
                    value={visibility}
                    onChange={handleVisibilityChange}
                    className="w-full border rounded-md px-3 py-2 mt-2 focus:outline-none focus:ring focus:border-blue-300"
                  >
                    <option value="public">Public</option>
                    <option value="private">Private</option>
                  </select>
                </div>
              </div>
            </div>
          </div>
          <div className="bg-gray-50 px-4 py-3 sm:px-6 sm:flex sm:flex-row-reverse">
            <button
              onClick={handleSave}
              className="w-full inline-flex justify-center rounded-md border border-transparent px-4 py-2 bg-blue-600 text-base font-medium text-white hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500 sm:ml-3 sm:w-auto sm:text-sm"
            >
              Save
            </button>
            <button
              onClick={cancelSave}
              className="mt-3 w-full inline-flex justify-center rounded-md border border-gray-300 px-4 py-2 bg-white text-base font-medium text-gray-700 hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500 sm:mt-0 sm:ml-3 sm:w-auto sm:text-sm"
            >
              Cancel
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Modal;
