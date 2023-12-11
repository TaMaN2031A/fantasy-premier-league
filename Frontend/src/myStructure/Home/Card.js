import logo from './assets/Lion.png'
import './Card.css';


function Card(props) {

    function handleClick(e) {
      // Your logic or actions here when the div is clicked
      console.log(Event); 
      // Add any other functionality you want to execute

      // Navigate to the page
    }
  
    return (
      <button className='relative bg-grey w-1/5 h-40 rounded-3xl mx-8 my-20 text-gray-200 font-mono shadow-2xl
        border-8 border-grey p-4 transition-all duration-500 hover:scale-110 hover:ring-4 ring-gold card-shine-effect'
        onClick={handleClick}>
          {/* header */}
          <div className='px-0 py-0 font-bold text-2xl tracking-widest'>
             {props.Title}  
          </div>
  
          <div className='absolute top-0 right-2 opacity-70'>
            <img
            src={logo} 
            alt='Logo'
            className="object-contain w-16 h-16"
            />
          </div>
          {/* details */}
          <div className='py-5'>
            {props.details}
          </div>
  
      </button>
    );
  }
  
  export default Card;