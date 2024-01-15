import "./FilterPanel.css";
import { useState, useEffect } from "react";
import axios from "axios";
import { Select, Slider } from "antd";
import { useNavigate, useLocation } from 'react-router-dom';

function FilterPanel({handleFilterChange}) {
    const [selectedCategories, setSelectedCategories] = useState([]);
    const [allCategories, setAllCategories] = useState([]);
    const [priceRange, setPriceRange] = useState([]);
    const navigate = useNavigate();
    const location = useLocation();
    const queryParams = new URLSearchParams(location.search);

    const getHarvestCategories = async () => {
        const response = await axios.get('http://localhost:8080/api/farmer/harvest-categories');
        if(response.status == 200){
            setAllCategories(response.data.map((item) => {
                return {
                    value: item.categoryId,
                    label: item.categoryName
                }
            }));
        }   
    };
    
    const handleSelect = (value) => {
        setSelectedCategories([...selectedCategories, value]);
      };

    const handleDeselect = (value) => {
        const updatedValues = selectedCategories.filter((category) => category !== value);
        setSelectedCategories(updatedValues);
    };

    const handlePriceChange = (value) => {
        setPriceRange(value);
    }


    useEffect(() => {
        getHarvestCategories();
        const categoriesParam = queryParams.get('categories');
        if (categoriesParam) {
            setSelectedCategories(categoriesParam.split(','));
        }
    }, [location.search]);

    useEffect(() => {
        console.log(selectedCategories + " " + priceRange);
        handleFilterChange(selectedCategories.join(','), priceRange);
    }, [selectedCategories, priceRange]);

    const handleCategoryChange = (values) => {
        const newSearchParams = new URLSearchParams();
        if (values.length > 0) {
            newSearchParams.set('categories', values.join(','));
        } else if (values.length == 0){
            newSearchParams.delete('categories');
        }
        const queryString = newSearchParams.toString();
        const url = queryString ? `/customer/shop?${queryString}` : '/customer/shop';
        navigate(url);
    }

    return (
        <>
            <h4>FILTERS</h4><br></br>
            <h6>CATEGORY</h6>
            <Select mode="multiple" allowClear style={{ width: '100%',}} placeholder="Please select category"
                onSelect={handleSelect}
                onDeselect={handleDeselect}
                options={allCategories}
                value={selectedCategories} // Set the default selected values
            />
            <br/><br/>

            <h6>PRICE (PER QUANTITY)</h6>
            <Slider range step={10} min={0} max={1000} defaultValue={[0, 1000]} onChangeComplete={(value) => handlePriceChange(value)}/>
      
        </>
    )
}


export default FilterPanel;