package com.skillbarter.config;

import com.skillbarter.entity.*;
import com.skillbarter.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {
    
    @Autowired
    private SkillCategoryRepository categoryRepository;
    
    @Autowired
    private SkillRepository skillRepository;
    
    @Bean
    CommandLineRunner initDatabase() {
        return args -> {
            // Check if data already exists
            if (categoryRepository.count() > 0) {
                return; // Data already initialized
            }
            
            System.out.println("Initializing database with seed data...");
            
            // Create categories
            SkillCategory tech = createCategory("Technology", "Programming and IT skills");
            SkillCategory music = createCategory("Music", "Musical instruments and production");
            SkillCategory language = createCategory("Language", "Language learning and translation");
            SkillCategory fitness = createCategory("Fitness", "Sports and physical training");
            SkillCategory cooking = createCategory("Cooking", "Culinary skills");
            SkillCategory business = createCategory("Business", "Marketing and business skills");
            SkillCategory art = createCategory("Art & Design", "Creative and design skills");
            
            // Create skills for each category
            // Technology skills
            createSkill("Java Programming", "Java development including Spring Boot", tech);
            createSkill("Python Programming", "Python scripting and development", tech);
            createSkill("JavaScript", "Frontend and Node.js development", tech);
            createSkill("React", "React.js frontend framework", tech);
            createSkill("Angular", "Angular frontend framework", tech);
            createSkill("Web Development", "HTML, CSS, and web design", tech);
            createSkill("Mobile Development", "iOS and Android app development", tech);
            createSkill("Database Design", "SQL and NoSQL database design", tech);
            
            // Music skills
            createSkill("Guitar Lessons", "Acoustic and electric guitar", music);
            createSkill("Piano Lessons", "Classical and modern piano", music);
            createSkill("Singing", "Vocal training and technique", music);
            createSkill("Music Production", "DAW and audio production", music);
            createSkill("Drums", "Drum kit and percussion", music);
            
            // Language skills
            createSkill("English Tutoring", "English language teaching", language);
            createSkill("Spanish Tutoring", "Spanish language teaching", language);
            createSkill("French Tutoring", "French language teaching", language);
            createSkill("Mandarin Chinese", "Chinese language teaching", language);
            createSkill("Translation Services", "Document and verbal translation", language);
            
            // Fitness skills
            createSkill("Personal Training", "Fitness and strength training", fitness);
            createSkill("Yoga", "Various yoga styles and meditation", fitness);
            createSkill("Martial Arts", "Karate, Taekwondo, BJJ", fitness);
            createSkill("Running Coach", "Marathon and running training", fitness);
            createSkill("Nutrition Consulting", "Diet and meal planning", fitness);
            
            // Cooking skills
            createSkill("Baking", "Bread, cakes, and pastries", cooking);
            createSkill("Italian Cuisine", "Pasta, pizza, and Italian dishes", cooking);
            createSkill("Asian Cuisine", "Chinese, Japanese, Thai cooking", cooking);
            createSkill("Vegetarian Cooking", "Plant-based recipes", cooking);
            createSkill("Meal Prep", "Batch cooking and meal planning", cooking);
            
            // Business skills
            createSkill("Digital Marketing", "SEO, SEM, and social media", business);
            createSkill("Graphic Design", "Logo and brand design", business);
            createSkill("Content Writing", "Blog and copywriting", business);
            createSkill("Business Consulting", "Strategy and planning", business);
            createSkill("Accounting", "Bookkeeping and tax preparation", business);
            
            // Art & Design skills
            createSkill("Photography", "Portrait and landscape photography", art);
            createSkill("Video Editing", "Video production and editing", art);
            createSkill("Drawing", "Pencil, charcoal, and digital drawing", art);
            createSkill("Painting", "Oil, watercolor, and acrylic painting", art);
            createSkill("UI/UX Design", "User interface and experience design", art);
            
            System.out.println("Database initialization complete!");
        };
    }
    
    private SkillCategory createCategory(String name, String description) {
        SkillCategory category = new SkillCategory();
        category.setName(name);
        category.setCategoryDescription(description);
        category.setActive(true);
        return categoryRepository.save(category);
    }
    
    private Skill createSkill(String name, String description, SkillCategory category) {
        Skill skill = new Skill();
        skill.setName(name);
        skill.setSkillDescription(description);
        skill.setCategory(category);
        skill.setVerified(true);
        skill.setUsageCount(0);
        return skillRepository.save(skill);
    }
}
