import React from 'react';

const Pagination = ({ currentPage, totalPages, onPageChange }) => {
    const generatePageButtons = () => {
        const buttons = [];
        const visiblePages = 3; // You can adjust the number of visible pages

        for (let i = 1; i <= totalPages; i++) {
            if (
                i === 1 ||
                i === totalPages ||
                (i >= currentPage - visiblePages && i <= currentPage + visiblePages)
            ) {
                buttons.push(
                    <li key={i}>
                        <a
                            href="#"
                            onClick={() => onPageChange(i)}
                            className={`flex items-center justify-center text-sm py-2 px-3 leading-tight ${
                                i === currentPage
                                    ? 'text-primary-600 bg-primary-50 border border-primary-300 hover:bg-primary-100 hover:text-primary-700 dark:border-gray-700 dark:bg-gray-700 dark:text-white'
                                    : 'text-gray-500 bg-white border border-gray-300 hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white'
                            }`}
                        >
                            {i}
                        </a>
                    </li>
                );
            } else if (
                (i === currentPage - visiblePages - 1 && currentPage - visiblePages > 2) ||
                (i === currentPage + visiblePages + 1 && currentPage + visiblePages < totalPages - 1)
            ) {
                buttons.push(
                    <li key={i}>
            <span className="flex items-center justify-center text-sm py-2 px-3 leading-tight text-gray-500">
              ...
            </span>
                    </li>
                );
            }
        }

        return buttons;
    };

    return (
        <ul className="inline-flex items-stretch -space-x-px">
            <li>
                <button
                    onClick={() => onPageChange(currentPage - 1)}
                    className={`flex items-center justify-center h-full py-1.5 px-3 ml-0 text-gray-500 bg-white rounded-l-lg border border-gray-300 ${currentPage === 1 ? 'opacity-50 cursor-not-allowed' : 'hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white'}`}
                    disabled={currentPage === 1}
                >
                    <span className="sr-only">Previous</span>
                    <svg
                        className="w-5 h-5"
                        aria-hidden="true"
                        fill="currentColor"
                        viewBox="0 0 20 20"
                        xmlns="http://www.w3.org/2000/svg"
                    >
                        <path
                            fillRule="evenodd"
                            d="M12.707 5.293a1 1 0 010 1.414L9.414 10l3.293 3.293a1 1 0 01-1.414 1.414l-4-4a1 1 0 010-1.414l4-4a1 1 0 011.414 0z"
                            clipRule="evenodd"
                        />
                    </svg>
                </button>
            </li>

            {generatePageButtons()}

            <li>
                <button
                    onClick={() => onPageChange(currentPage + 1)}
                    className={`flex items-center justify-center h-full py-1.5 px-3 leading-tight text-gray-500 bg-white rounded-r-lg border border-gray-300 ${currentPage === totalPages ? 'opacity-50 cursor-not-allowed' : 'hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white'}`}
                    disabled={currentPage === totalPages}
                >
                    <span className="sr-only">Next</span>
                    <svg
                        className="w-5 h-5"
                        aria-hidden="true"
                        fill="currentColor"
                        viewBox="0 0 20 20"
                        xmlns="http://www.w3.org/2000/svg"
                    >
                        <path
                            fillRule="evenodd"
                            d="M7.293 14.707a1 1 0 010-1.414L10.586 10 7.293 6.707a1 1 0 011.414-1.414l4 4a1 1 0 010 1.414l-4 4a1 1 0 01-1.414 0z"
                            clipRule="evenodd"
                        />
                    </svg>
                </button>
            </li>
        </ul>
    );
};

export default Pagination;
