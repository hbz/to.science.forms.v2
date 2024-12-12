function updateKtblOptions() {
    // Funktion, die beim Ändern des ersten Selects ausgeführt wird
    $('#livestockcategory0').change(function() {
        var selectedCategory = $(this).val(); // Wert des ersten Selects
		console.log("selectedCategory: " + selectedCategory);
		
		//const example = new UseBootstrapSelect(document.getElementById('#livestockproduction0'));
		//if(example.getValue()!=null) {
			//example.setValue("");
			//clearValue(){this.setSelected(this.getSelected().map(t=>t.value),!1)}
		//}
		$('#livestockproduction0 option:selected').remove();
        // Alle Optionen im zweiten Select reaktivieren (falls sie zuvor deaktiviert wurden)
        $('#livestockproduction0 option').prop('disabled', false);

        // Abhängig von der Auswahl im ersten Select deaktivieren wir die entsprechenden Optionen im zweiten Select
        if (selectedCategory === 'cattle') {
            // Deaktivieren von Optionen, die nicht angezeigt werden sollen
            $('#livestockproduction0 option[value="diary_cattle_farming"]').prop('disabled', true);
            $('#livestockproduction0 option[value="young_cattle_farming"]').prop('disabled', true);
            $('#livestockproduction0 option[value="calf_raising"]').prop('disabled', true);
            $('#livestockproduction0 option[value="calf_fattening"]').prop('disabled', true);
            $('#livestockproduction0 option[value="cattle_fattening"]').prop('disabled', true);
            $('#livestockproduction0 option[value="suckler_cow_farming"]').prop('disabled', true);
            
            $('#additionalhousingsystems0 option[value="solid_manure"]').prop('disabled', true);
            $('#additionalhousingsystems0 option[value="liquid_manure"]').prop('disabled', true);
            $('#additionalhousingsystems0 option[value="solid_floor"]').prop('disabled', true);
            $('#additionalhousingsystems0 option[value="perforated_floor"]').prop('disabled', true);
            $('#additionalhousingsystems0 option[value="pastures"]').prop('disabled', true);
            $('#additionalhousingsystems0 option[value="yard"]').prop('disabled', true);
            $('#additionalhousingsystems0 option[value="others"]').prop('disabled', true);
        } else if (selectedCategory === 'pig') {
            // Deaktivieren von Optionen, die nicht angezeigt werden sollen
            $('#livestockproduction0 option[value="piglet_production"]').prop('disabled', true);
            $('#livestockproduction0 option[value="piglet_raising"]').prop('disabled', true);
            $('#livestockproduction0 option[value="pig_fattening"]').prop('disabled', true);
            
            $('#additionalhousingsystems0 option[value="solid_manure"]').prop('disabled', true);
            $('#additionalhousingsystems0 option[value="liquid_manure"]').prop('disabled', true);
            $('#additionalhousingsystems0 option[value="solid_floor"]').prop('disabled', true);
            $('#additionalhousingsystems0 option[value="perforated_floor"]').prop('disabled', true);
            $('#additionalhousingsystems0 option[value="pastures"]').prop('disabled', true);
            $('#additionalhousingsystems0 option[value="yard"]').prop('disabled', true);
            $('#additionalhousingsystems0 option[value="others"]').prop('disabled', true);
        } else if (selectedCategory === 'chicken') {
            // Deaktivieren von Optionen, die nicht angezeigt werden sollen
            $('#livestockproduction0 option[value="broiler_fattening"]').prop('disabled', true);
            $('#livestockproduction0 option[value="laying_hen_farming"]').prop('disabled', true);
            $('#livestockproduction0 option[value="young_hen_farming"]').prop('disabled', true);
            
            $('#additionalhousingsystems0 option[value="solid_manure"]').prop('disabled', true);
            $('#additionalhousingsystems0 option[value="perforated_floor"]').prop('disabled', true);
            $('#additionalhousingsystems0 option[value="manure_belt"]').prop('disabled', true);
            $('#additionalhousingsystems0 option[value="veranda"]').prop('disabled', true);
            $('#additionalhousingsystems0 option[value="yard"]').prop('disabled', true);
            $('#additionalhousingsystems0 option[value="others"]').prop('disabled', true);
        } else if (selectedCategory === 'turkey') {
            // Deaktivieren von Optionen, die nicht angezeigt werden sollen
            $('#livestockproduction0 option[value="turkey_fattening"]').prop('disabled', true);
            $('#livestockproduction0 option[value="turkey_breeding"]').prop('disabled', true);
            
            $('#additionalhousingsystems0 option[value="solid_manure"]').prop('disabled', true);
            $('#additionalhousingsystems0 option[value="perforated_floor"]').prop('disabled', true);
            $('#additionalhousingsystems0 option[value="yard"]').prop('disabled', true);
            $('#additionalhousingsystems0 option[value="others"]').prop('disabled', true);
        } else if (selectedCategory === 'duck') {
            // Deaktivieren von Optionen, die nicht angezeigt werden sollen
            $('#livestockproduction0 option[value="duck_fattening"]').prop('disabled', true);
            
            $('#additionalhousingsystems0 option[value="solid_manure"]').prop('disabled', true);
            $('#additionalhousingsystems0 option[value="perforated_floor"]').prop('disabled', true);
            $('#additionalhousingsystems0 option[value="yard"]').prop('disabled', true);
            $('#additionalhousingsystems0 option[value="others"]').prop('disabled', true);
        }

    });

    // Initiales Auslösen der Funktion, um das zweite Select anfangs zu füllen
    $('#livestockproduction0').trigger('change');
    
    $('#livestockproduction0').change(function() {
        var selectedProduction = $(this).val(); // Wert des ersten Selects

        // Alle Optionen im zweiten Select reaktivieren (falls sie zuvor deaktiviert wurden)
        //$('#housingsystems0 option').prop('disabled', true);

        // Abhängig von der Auswahl im ersten Select deaktivieren wir die entsprechenden Optionen im zweiten Select
        if (selectedProduction === 'diary_cattle_farming' || selectedProduction === 'young_cattle_farming' || selectedProduction === 'cattle_fattening') {
            // Deaktivieren von Optionen, die nicht angezeigt werden sollen
            $('#housingsystems0 option[value="cubicle_h"]').prop('disabled', true);
            $('#housingsystems0 option[value="loose_h_1_type_floor"]').prop('disabled', true);
            $('#housingsystems0 option[value="loose_h_2_type_floor"]').prop('disabled', true);
            $('#housingsystems0 option[value="others"]').prop('disabled', true);
        } else if (selectedProduction === 'calf_raising') {
            // Deaktivieren von Optionen, die nicht angezeigt werden sollen
            $('#housingsystems0 option[value="calf_boxes_h"]').prop('disabled', true);
            $('#housingsystems0 option[value="igloo_or_hut_h"]').prop('disabled', true);
            $('#housingsystems0 option[value="loose_h_1_type_floor"]').prop('disabled', true);
            $('#housingsystems0 option[value="loose_h_2_type_floor"]').prop('disabled', true);
            $('#housingsystems0 option[value="others"]').prop('disabled', true);
        } else if (selectedProduction === 'calf_fattening' || selectedProduction === 'suckler_cow_farming') {
            // Deaktivieren von Optionen, die nicht angezeigt werden sollen
            $('#housingsystems0 option[value="loose_h_1_type_floor"]').prop('disabled', true);
            $('#housingsystems0 option[value="loose_h_2_type_floor"]').prop('disabled', true);
            $('#housingsystems0 option[value="others"]').prop('disabled', true);
        } else if (selectedProduction === 'pig_fattening' || selectedProduction === 'piglet_raising') {
            // Deaktivieren von Optionen, die nicht angezeigt werden sollen
            $('#housingsystems0 option[value="1_area_pen"]').prop('disabled', true);
            $('#housingsystems0 option[value="mult_area_pen"]').prop('disabled', true);
            $('#housingsystems0 option[value="others"]').prop('disabled', true);
        } else if (selectedProduction === 'piglet_production') {
            // Deaktivieren von Optionen, die nicht angezeigt werden sollen
            $('#housingsystems0 option[value="farrow_area_1_area_pen"]').prop('disabled', true);
            $('#housingsystems0 option[value="farrow_area_mult_area_pen"]').prop('disabled', true);
            $('#housingsystems0 option[value="wait_mate_area_mult_area_pen"]').prop('disabled', true);
            $('#housingsystems0 option[value="others"]').prop('disabled', true);
        } else if (selectedProduction === 'laying_hen_farming') {
            // Deaktivieren von Optionen, die nicht angezeigt werden sollen
            $('#housingsystems0 option[value="floor_h"]').prop('disabled', true);
            $('#housingsystems0 option[value="floor_h_with_aviaries"]').prop('disabled', true);
            $('#housingsystems0 option[value="mobile_h"]').prop('disabled', true);
            $('#housingsystems0 option[value="others"]').prop('disabled', true);
        } else if (selectedProduction === 'young_hen_farming') {
            // Deaktivieren von Optionen, die nicht angezeigt werden sollen
            $('#housingsystems0 option[value="floor_h"]').prop('disabled', true);
            $('#housingsystems0 option[value="floor_h_with_aviaries"]').prop('disabled', true);
            $('#housingsystems0 option[value="others"]').prop('disabled', true);
        } else if (selectedProduction === 'hens_fattening' || selectedProduction === 'turkey_fattening' || selectedProduction === 'turkey_raising' || selectedProduction === 'duck_fattening') {
            // Deaktivieren von Optionen, die nicht angezeigt werden sollen
            $('#housingsystems0 option[value="floor_h"]').prop('disabled', true);
            $('#housingsystems0 option[value="others"]').prop('disabled', true);
        }
    });
};


//var ready = (callback) => {
//     if (document.readyState != "loading") callback();
//     else document.addEventListener("DOMContentLoaded", callback);
//}
// 
//ready(() => {
//  	markSelectedLivestockCategory();
//  	updateHousingSystemsOptions();
//  	updateAdditionalHousingOptions();
//  	document.getElementById('livestockcategory0').addEventListener('change', function() { 
//	  	markSelectedLivestockCategory();
//	  	updateAdditionalHousingOptions();
//  	});
//  	document.getElementById('livestockproduction0').addEventListener('change', function() { 
//  		updateHousingSystemsOptions();
//	});
//});
//
//function enableOptions(values) {
//    var livestockProductionOptions = document.getElementById('livestockproduction0').getElementsByTagName('option');
//    for (var i = 0; i < livestockProductionOptions.length; i++) {
//        livestockProductionOptions[i].disabled = !values.includes(livestockProductionOptions[i].value);
//    }
//}
//
//function markSelectedLivestockCategory() {
//    var livestockCategory = document.getElementById('livestockcategory0').value;
//    var livestockProductionOptions = document.getElementById('livestockproduction0').getElementsByTagName('option');
//    for (var j = 0; j < livestockProductionOptions.length; j++) {		
//		 livestockProductionOptions[j].disabled = true;
//    }
//	if (livestockCategory !== "") {
//		livestockProductionOptions[0].disabled = false;
//	}else{
//		livestockProductionOptions[0].disabled = true;
//		livestockProductionOptions[0].selected = true;
//	}
//   
//    if (livestockCategory === 'cattle') {
//        enableOptions(['dairy_cattle_farming', 'young_cattle_farming', 'calf_raising', 'calf_fattening', 'cattle_fattening', 'suckler_cow_farming']);
//    } else if (livestockCategory === 'pig') {
//        enableOptions(['piglet_production', 'piglet_raising', 'pig_fattening']);
//    } else if (livestockCategory === 'chicken') {
//        enableOptions(['laying_hen_farming', 'young_hen_farming', 'broiler_fattening']);
//    } else if (livestockCategory === 'turkey') {
//        enableOptions(['turkey_fattening', 'turkey_raising']);
//    } else if (livestockCategory === 'duck') {
//        enableOptions(['duck_fattening']);
//    }
//    selectFirstEnabledOption(document.getElementById('livestockproduction0')); 
//    updateHousingSystemsOptions();
//}
//
//function selectFirstEnabledOption(selectElement) {
//    var options = selectElement.getElementsByTagName('option');
//    if (options.length > 0) {
//        options[0].disabled = false; // Aktiviere die erste Option
//     }
//    for (var i = 1; i < options.length; i++) { 
//        if (!options[i].disabled) {
//            options[i].selected = true; 
//            break; 
//        }
//    }
//}
//function updateHousingSystemsOptions() {
//  const selectedLivestockProduction = document.getElementById('livestockproduction0').value;
//  const housingSystemsOptions = document.getElementById('housingsystems0').getElementsByTagName('option');
//  const enabledOptions = getEnabledHousingOptions(selectedLivestockProduction);
//  const housingSystemsSelect = document.getElementById('housingsystems0');
//
//  if (selectedLivestockProduction === "") {
//        housingSystemsSelect.value = ""; // 
//        for (let i = 0; i < housingSystemsOptions.length; i++) {
//            housingSystemsOptions[i].disabled = true;
//        }
//        return; 
//    }
//  for (let i = 0; i < housingSystemsOptions.length; i++) {
//    housingSystemsOptions[i].disabled = true;
//  }
//  	housingSystemsOptions[0].disabled = false;
//  
//  for (let i = 0; i < housingSystemsOptions.length; i++) {
//    if (enabledOptions.has(housingSystemsOptions[i].value)) {
//      housingSystemsOptions[i].disabled = false;
//    }
//  }
//  selectFirstEnabledOption(document.getElementById('housingsystems0'));
//}
//function getEnabledHousingOptions(selectedLivestockProduction) {
//  const enabledOptions = new Set([
//    'others',
//    ...(selectedLivestockProduction === 'dairy_cattle_farming' || selectedLivestockProduction === 'young_cattle_farming' || selectedLivestockProduction === 'cattle_fattening'
//      ? ['cubicle_h', 'loose_h_1_type_floor', 'loose_h_2_type_floor']
//      : []),
//    ...(selectedLivestockProduction === 'calf_raising'
//      ? ['calf_boxes_h', 'igloo_or_hut_h', 'loose_h_1_type_floor', 'loose_h_2_type_floor']
//      : []),
//    ...(selectedLivestockProduction === 'calf_fattening' || selectedLivestockProduction === 'suckler_cow_farming'
//      ? ['loose_h_1_type_floor', 'loose_h_2_type_floor']
//      : []),
//    ...(selectedLivestockProduction === 'pig_fattening' || selectedLivestockProduction === 'piglet_raising'
//      ? ['1_area_pen', 'mult_area_pen']
//      : []),
//    ...(selectedLivestockProduction === 'piglet_production'
//      ? ['farrow_area_1_area_pen', 'farrow_area_mult_area_pen', 'wait_mate_area_mult_area_pen']
//      : []),
//    ...(selectedLivestockProduction === 'laying_hen_farming'
//      ? ['floor_h', 'floor_h_with_aviaries', 'mobile_h']
//      : []),
//    ...(selectedLivestockProduction === 'young_hen_farming'
//      ? ['floor_h', 'floor_h_with_aviaries']
//      : []),
//    ...(selectedLivestockProduction === 'turkey_fattening' || selectedLivestockProduction === 'turkey_raising' || selectedLivestockProduction === 'duck_fattening'
//      ? ['floor_h']
//      : []),
//  ]);
//  return enabledOptions;
//}
//function updateAdditionalHousingOptions() {
//  const livestockDropdown = document.getElementById('livestockcategory0');
//  const housingDropdowns = document.querySelectorAll('[id^="additionalhousingsystems0"]');
//  const selectedLivestock = livestockDropdown.value;
//  const enabledAdditionalHousingOptions = getEnabledAdditionalHousingOptions(selectedLivestock);
//  housingDropdowns.forEach((dropdown) => {
//    const dropdownOptions = dropdown.getElementsByTagName('option');
//    for (let i = 0; i < dropdownOptions.length; i++) {
//      dropdownOptions[i].disabled = true;
//    }
//	dropdownOptions[0].disabled = false;
//
//    for (let i = 0; i < dropdownOptions.length; i++) {
//      if (enabledAdditionalHousingOptions.has(dropdownOptions[i].value)) {
//        dropdownOptions[i].disabled = false;
//      }
//    }
//	if (selectedLivestock === "") {
//            dropdown.value = ""; 
//        } else {
//            selectFirstEnabledOption(dropdown);
//        }
//  });
//}
//function getEnabledAdditionalHousingOptions(selectedLivestock) {
//  const enabledOptions = new Set();
//
//  if (['cattle', 'pig', 'chicken', 'turkey', 'duck'].includes(selectedLivestock)) {
//    enabledOptions.add('solid_manure');
//	enabledOptions.add('perforated_floor');
//	enabledOptions.add('yard');
//	enabledOptions.add('others');
//  }
//  if (['cattle', 'pig'].includes(selectedLivestock)) {
//    enabledOptions.add('liquid_manure');
//    enabledOptions.add('solid_floor');
//    enabledOptions.add('pastures');
//    enabledOptions.add('others');	
//  } 
//   if (['chicken'].includes(selectedLivestock)) {
//   enabledOptions.add('manure_belt');
//    enabledOptions.add('veranda');	
//  }
//  return enabledOptions;
//}