package employee.domain.service.employee;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import employee.domain.model.Employee;
import employee.domain.repository.employee.EmployeeRepository;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Inject
    EmployeeRepository employeeRepository;
    
    @Override
    @Transactional(readOnly = true) 
    public Page<Employee> findAll(Pageable pageable) {
    	Long total = employeeRepository.count();
    	List<Employee> content;
    	if (total > 0) {
			content = employeeRepository.findAll(pageable);
		}
		else {
			content = Collections.emptyList();
		}
		Page<Employee> page = new PageImpl<>(content, pageable, total);
		return page;
    }
    
    @Override
    public Page<Employee> search(String searchCondition,Pageable pageable) {
    	System.out.println("Page size is" + pageable.getPageSize());
    	System.out.println("Offset is" + pageable.getOffset());
    	Long total = employeeRepository.countById(searchCondition);
    	System.out.println("Total is ="+ total);
    	List<Employee> content;
    	if (total > 0) {
			content = employeeRepository.search(pageable,searchCondition);
		}
		else {
			content = Collections.emptyList();
		}
		Page<Employee> page = new PageImpl<>(content, pageable, total);
		return page;
    }

	@Override
	public Employee create(Employee emp) {
		employeeRepository.create(emp);
        /* REMOVE THIS LINE IF YOU USE JPA
            todoRepository.save(todo); // 10
           REMOVE THIS LINE IF YOU USE JPA */
		return emp;
	}

	@Override
	public Employee finish(String employeeId) {
		return null;
	}

	@Override
	public void delete(String employeeId) {
		employeeRepository.delete(employeeId);
	}

	@Override
	public Employee findOne(String employeeId) {
		return employeeRepository.findOne(employeeId);
	}
	
	public long count() {
	    return employeeRepository.count();
	}

	@Override
	public UploadFileInfo createFile(UploadFileInfo file) {
		employeeRepository.createFile(file);
        /* REMOVE THIS LINE IF YOU USE JPA
            todoRepository.save(todo); // 10
           REMOVE THIS LINE IF YOU USE JPA */
		return file;
	}



}
