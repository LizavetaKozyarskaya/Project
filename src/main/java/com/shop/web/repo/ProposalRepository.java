package com.shop.web.repo;

import com.shop.web.models.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ProposalRepository extends JpaRepository <Proposal, Long>{

//   List<Proposal> findById_Users(Long id_Users);

}
