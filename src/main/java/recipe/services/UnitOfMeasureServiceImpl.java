/*
 *  Created By  Zaynab Osama ,  On 2/23/19 2:46 PM
 *
 */

package recipe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recipe.commands.UnitOfMeasureCommand;
import recipe.converters.UnitOfMeasureToUnitOfMeasureCommand;
import recipe.domain.UnitOfMeasure;
import recipe.exceptions.NotFoundException;
import recipe.repositories.UnitOfMeasureRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    @Autowired
    private UnitOfMeasureRepository unitOfMeasureRepo;
    @Autowired
    private UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;


    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository, UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
       this.unitOfMeasureRepo = unitOfMeasureRepository;
       this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
    }

    @Override
    public void saveUnit(UnitOfMeasure unitOfMeasure) {
        unitOfMeasureRepo.save(unitOfMeasure);
    }

    @Override
    public Optional<UnitOfMeasure> findByUom(String uom) {
       return Optional.of(new UnitOfMeasure());
               //Optional.ofNullable(unitOfMeasureRepo.findByUom(uom).orElseThrow(() -> new NotFoundException("Unit Not Found With " + uom)));
    }

    @Override
    public Set<UnitOfMeasure> findAllUnitOfMeasures() {
        Set<UnitOfMeasure> unitOfMeasures = new HashSet<UnitOfMeasure>();
        unitOfMeasureRepo.findAll().iterator().forEachRemaining(unitOfMeasures::add);
        return unitOfMeasures;
    }

    @Override
    public Set<UnitOfMeasureCommand> getAllCommandUoms() {
        return StreamSupport.stream(unitOfMeasureRepo.findAll()
                .spliterator(), false)
                .map(unitOfMeasureToUnitOfMeasureCommand::convert)
                .collect(Collectors.toSet());
    }
}
