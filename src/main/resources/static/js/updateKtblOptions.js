function updateKtblOptions() {

	$('#livestockcategory0').on('change', function() {
        var selectedCategory = $(this).val();
        
        var $productionSelect = $('#livestockproduction0');
        var $additionalhousingSelect = $('#additionalhousingsystems0');
        
        const livestockSelect = new UseBootstrapSelect(document.getElementById('livestockproduction0'));
        const addhousingSysSelect = new UseBootstrapSelect(document.getElementById('additionalhousingsystems0'));
        
        livestockSelect.clearValue();
        addhousingSysSelect.clearValue();
        
	    const optionsMap = {
	        cattle: {
	            production: ["diary_cattle_farming", "young_cattle_farming", "calf_raising", "calf_fattening", "cattle_fattening", "suckler_cow_farming"],
	            housing: ["solid_manure", "liquid_manure", "solid_floor", "perforated_floor", "pastures", "yard", "others"]
	        },
	        pig: {
	            production: ["piglet_production", "piglet_raising", "pig_fattening"],
	            housing: ["solid_manure", "liquid_manure", "solid_floor", "perforated_floor", "pastures", "yard", "others"]
	        },
	        chicken: {
	            production: ["laying_hen_farming", "young_hen_farming", "broiler_fattening"],
	            housing: ["solid_manure", "perforated_floor", "manure_belt", "veranda", "yard", "others"]
	        },
	        turkey: {
	            production: ["turkey_fattening", "turkey_raising"],
	            housing: ["solid_manure", "perforated_floor", "yard", "others"]
	        },
	        duck: {
	            production: ["duck_fattening"],
	            housing: ["solid_manure", "perforated_floor", "yard", "others"]
	        }
	    };
	    
    	function clearOptions($select) {
	        $select.find('option').each(function() {
	            if ($(this).val() !== "") {
	                $(this).prop('disabled', true);
	            }
	        });
	    }
	    
	    // Function to enable specific options
	    function enableOptions($select, options) {
	        options.forEach(option => {
	            $select.find(`option[value="${option}"]`).prop('disabled', false);
	        });
	    }
	    
	    clearOptions($productionSelect);
	    clearOptions($additionalhousingSelect);
	
	    if (optionsMap[selectedCategory]) {
	        enableOptions($productionSelect, optionsMap[selectedCategory].production);
	        enableOptions($additionalhousingSelect, optionsMap[selectedCategory].housing);
	    }

	    livestockSelect.update();
	    addhousingSysSelect.update();

    });
    
    $('#livestockproduction0').on('change', function() {
        var selectedProduction = $(this).val();

        var $housingSystemSelect = $('#housingsystems0');
        
        const housingSysSelect = new UseBootstrapSelect(document.getElementById('housingsystems0'));
        housingSysSelect.clearValue();
        
        const housingOptions = {
	        'diary_cattle_farming': ['cubicle_h', 'loose_h_1_type_floor', 'loose_h_2_type_floor', 'others'],
	        'young_cattle_farming': ['cubicle_h', 'loose_h_1_type_floor', 'loose_h_2_type_floor', 'others'],
	        'cattle_fattening': ['cubicle_h', 'loose_h_1_type_floor', 'loose_h_2_type_floor', 'others'],
	        'calf_raising': ['calf_boxes_h', 'igloo_or_hut_h', 'loose_h_1_type_floor', 'loose_h_2_type_floor', 'others'],
	        'calf_fattening': ['loose_h_1_type_floor', 'loose_h_2_type_floor', 'others'],
	        'suckler_cow_farming': ['loose_h_1_type_floor', 'loose_h_2_type_floor', 'others'],
	        'pig_fattening': ['1_area_pen', 'mult_area_pen', 'others'],
	        'piglet_raising': ['1_area_pen', 'mult_area_pen', 'others'],
	        'piglet_production': ['farrow_area_1_area_pen', 'farrow_area_mult_area_pen', 'wait_mate_area_mult_area_pen', 'others'],
	        'laying_hen_farming': ['floor_h', 'floor_h_with_aviaries', 'mobile_h', 'others'],
	        'young_hen_farming': ['floor_h', 'floor_h_with_aviaries', 'others'],
	        'hens_fattening': ['floor_h', 'others'],
	        'turkey_fattening': ['floor_h', 'others'],
	        'turkey_raising': ['floor_h', 'others'],
	        'duck_fattening': ['floor_h', 'others']
	    };
	    
	    var enabledOptions = housingOptions[selectedProduction] || [];
	
	    $housingSystemSelect.find('option').each(function() {
	        $(this).prop('disabled', !enabledOptions.includes($(this).val()) && $(this).val() !== "");
	    });
	
	    housingSysSelect.update();
    });
}	
	
