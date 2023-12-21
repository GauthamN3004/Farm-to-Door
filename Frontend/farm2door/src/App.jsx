import { useState } from 'react'
import './App.css'
import 'bootstrap/dist/css/bootstrap.min.css';

function App() {
  const [count, setCount] = useState(0)

  return (
    <div>
      <h1>Hello world</h1>
      <button className='btn btn-primary'>CLICK</button>
    </div>
  )
}

export default App
